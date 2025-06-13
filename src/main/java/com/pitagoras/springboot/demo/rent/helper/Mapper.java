package com.pitagoras.springboot.demo.rent.helper;

import com.pitagoras.springboot.demo.rent.dto.CarDto;
import com.pitagoras.springboot.demo.rent.dto.CarImageDto;
import com.pitagoras.springboot.demo.rent.entity.Car;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    public static CarDto convertToDto(Car car) {
        List<CarImageDto> imageDtos = car.getImages().stream()
                .map(img -> new CarImageDto(img.getId(), img.getImageUrl(), img.isPrimaryImage()))
                .collect(Collectors.toList());


        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setMake(car.getMake());
        dto.setModel(car.getModel());
        dto.setYear(car.getYear());
        dto.setColor(car.getColor());
        dto.setLicensePlate(car.getLicensePlate());
        dto.setAvailable(car.isAvailable());
        dto.setKmPassed(car.getKmPassed());
        dto.setDescription(car.getDescription());
        dto.setLitersPerKm(car.getLitersPerKm());
        dto.setTransmission(car.getTransmission());
        dto.setHasNavigation(car.getHasNavigation());
        dto.setPricePerDay(car.getPricePerDay());
        dto.setImages(imageDtos);
        return dto;
    }



}

