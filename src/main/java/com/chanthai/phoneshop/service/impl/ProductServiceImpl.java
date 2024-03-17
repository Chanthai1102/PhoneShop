package com.chanthai.phoneshop.service.impl;

import com.chanthai.phoneshop.entity.Product;
import com.chanthai.phoneshop.dto.ProductImportDTO;
import com.chanthai.phoneshop.entity.ProductImportHistory;
import com.chanthai.phoneshop.exception.APIException;
import com.chanthai.phoneshop.exception.ResourceNotFoundException;
import com.chanthai.phoneshop.mapper.ProductMapper;
import com.chanthai.phoneshop.repository.ProductImportHistoryRepository;
import com.chanthai.phoneshop.repository.ProductRepository;
import com.chanthai.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository importHistoryRepository;
    private final ProductMapper productMapper;
    @Override
    public Product create(Product product) {
        String name = "%s %s".formatted(product.getModel().getName(),product.getColor().getName());
        product.setName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getByID(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product",id));
    }

    @Override
    public Product getByModelIdAndColorId(Long modelId, Long colorId) {
        return productRepository.findByModelIdAndColorId(modelId,colorId)
                .orElseThrow(() -> new APIException(HttpStatus.BAD_REQUEST,"Product not found with color ID : %d and Color : %d".formatted(modelId,colorId)));
    }

    @Override
    public void importProduct(ProductImportDTO importDTO) {
        if (importDTO.getImportUnit() == null){
            throw new APIException(HttpStatus.BAD_REQUEST,"Import unit can't be null");
        }
        //Update availableUnit Product
        Product product = getByID(importDTO.getProductId());
        Integer availableUnit = 0;
        if (product.getAvailableUnit() != null){
            availableUnit = product.getAvailableUnit();
        }
        product.setAvailableUnit(availableUnit + importDTO.getImportUnit());
        productRepository.save(product);

        //save Product Import History
        ProductImportHistory productImportHistory = productMapper.toProductImportHistory(importDTO,product);
        importHistoryRepository.save(productImportHistory);
    }

    @Override
    public void setSalePrice(Long productId, BigDecimal price) {
        Product product = getByID(productId);
        product.setSalePrice(price);
        productRepository.save(product);
    }

    @Override
    public void validateStock(Long productId, Integer numberOfUnit) {
        Product product = getByID(productId);
    }

    @Override
    public Map<Integer,String> uploadProduct(MultipartFile file)  {
        Map<Integer, String> map = new HashMap<>();
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet("products");
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()){
                int rowNumber = 0;
                try {
                    Row row = rowIterator.next();

                    int cellIndex = 0;

                    Cell cellNo = row.getCell(cellIndex++);
                    rowNumber = (int) cellNo.getNumericCellValue();

                    Cell cellModelId = row.getCell(cellIndex++);
                    Long modelId = (long) cellModelId.getNumericCellValue();
                    Cell cellColorId = row.getCell(cellIndex++);
                    Long colorId = (long) cellColorId.getNumericCellValue();

                    Cell cellPriceImport = row.getCell(cellIndex++);
                    double importPrice = cellPriceImport.getNumericCellValue();

                    Cell cellImportUnit = row.getCell(cellIndex++);
                    Integer importUnit = (int) cellImportUnit.getNumericCellValue();
                    if (importUnit < 1){
                        throw new APIException(HttpStatus.BAD_REQUEST,"Unit must be Greater than 0");
                    }

                    Cell cellImportDate = row.getCell(cellIndex++);
                    LocalDateTime importDate = cellImportDate.getLocalDateTimeCellValue();

                    Product product = getByModelIdAndColorId(modelId,colorId);

                    Integer availableUnit = 0;
                    if (product.getAvailableUnit() != null){
                        availableUnit = product.getAvailableUnit();
                    }
                    product.setAvailableUnit(availableUnit + importUnit);
                    productRepository.save(product);

                    //save Product Import History
                    ProductImportHistory importHistory = new ProductImportHistory();
                    importHistory.setDateImport(importDate);
                    importHistory.setImportUnit(importUnit);
                    importHistory.setPricePerUnit(BigDecimal.valueOf(importPrice));
                    importHistory.setProduct(product);
                    importHistoryRepository.save(importHistory);
                }catch (Exception e){
                    map.put(rowNumber,e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
