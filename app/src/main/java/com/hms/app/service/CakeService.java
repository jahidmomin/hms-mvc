package com.hms.app.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.app.dto.Cake;

@Service
public class CakeService {
    public List<Cake> getAllCakes() {
        return Arrays.asList(
                new Cake(1L, "Chocolate Heaven", "Delicious chocolate cake", "https://picsum.photos/200/200"),
                new Cake(2L, "Vanilla Dream", "Creamy vanilla cake", "https://picsum.photos/200/200"),
                new Cake(3L, "Strawberry Delight", "Fresh strawberry cake", "https://picsum.photos/200/200"));
    }
}
