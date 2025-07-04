package com.pitagoras.springboot.demo.rent.service;


import com.pitagoras.springboot.demo.rent.entity.CarImage;

import java.util.List;
import java.util.Optional;

public interface CarImageService {


    CarImage addImageToCar(int carId, String imageUrl, boolean primary);

    List<CarImage> getImagesByCar(int carId);

}


