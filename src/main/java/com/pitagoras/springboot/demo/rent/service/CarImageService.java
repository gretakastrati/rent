package com.pitagoras.springboot.demo.rent.service;


import com.pitagoras.springboot.demo.rent.entity.CarImage;

import java.util.List;

public interface CarImageService {


    CarImage addImageToCar(int carId, String imageUrl, boolean primary);

    List<CarImage> getImagesByCar(int carId);

}


