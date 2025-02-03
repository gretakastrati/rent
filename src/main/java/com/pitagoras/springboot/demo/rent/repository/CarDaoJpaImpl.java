package com.pitagoras.springboot.demo.rent.repository;

import com.pitagoras.springboot.demo.rent.entity.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.context.annotation.Primary;

import java.util.List;
@Primary
public class CarDaoJpaImpl implements CarDAO{

    private EntityManager entityManager;

    CarDaoJpaImpl(EntityManager entityManager) {

        this.entityManager = entityManager;
    }


    @Override
    public void save(Car theCar) {
        Car car = this.entityManager.merge(theCar);

    }

    @Override
    public Car findById(int id) {
        Car car = this.entityManager.find(Car.class,id);
        return car;
    }

    @Override
    public void updateCar(Car theCar) {
        Car car = this.entityManager.merge(theCar);

    }


    @Override
    public void deleteCar(Car theCar) {
        this.entityManager.remove(theCar);

    }

    @Override
    public List<Car> findAll() {
        TypedQuery<Car> theQuery = entityManager.createQuery("FROM Car", Car.class);
        return theQuery.getResultList();
    }

    @Override
    public Car find(String licensePlate) {
        TypedQuery<Car> theQuery = entityManager.createQuery(
                "FROM Car WHERE licensePlate=:tabllat", Car.class);
        theQuery.setParameter("tabllat", licensePlate);
        List<Car> result =  theQuery.getResultList();
        if(result.size() == 0){
            return null;
        }
        return result.get(0);


    }
}
