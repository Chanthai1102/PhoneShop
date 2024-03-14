package com.chanthai.phoneshop.service;

import com.chanthai.phoneshop.entity.Color;

public interface ColorService {
    Color create(Color color);
    Color getById(Long id);
}
