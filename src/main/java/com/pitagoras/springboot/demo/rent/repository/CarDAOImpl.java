package com.pitagoras.springboot.demo.rent.repository;

import com.pitagoras.springboot.demo.rent.entity.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class CarDAOImpl implements CarDAO{

    private EntityManager entityManager;

    @Autowired
    public CarDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Car theCar) {
        entityManager.persist(theCar);
    }

    @Override
    @Transactional
    public Car findById(int id) {
        return entityManager.find(Car.class,id);
    }

    @Override
    @Transactional
    public void updateCar(Car theCar) {
        entityManager.merge(theCar);
    }

    @Override
    @Transactional
    public void deleteCar(Car theCar) {
        entityManager.remove(theCar);
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