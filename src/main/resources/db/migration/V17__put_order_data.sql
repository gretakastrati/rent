-- Car ID: 6
INSERT INTO orders (customer_id, car_id, rental_start_date, rental_end_date, total_price, status,
                    pickup_location, drop_location, created_at, updated_at)
VALUES (1, 6, '2025-06-10', '2025-06-12', 150.00, 'CONFIRMED', 'New York', 'Boston', NOW(), NOW());

-- Car ID: 8
INSERT INTO orders (customer_id, car_id, rental_start_date, rental_end_date, total_price, status,
                    pickup_location, drop_location, created_at, updated_at)
VALUES (2, 8, '2025-06-11', '2025-06-15', 300.00, 'CONFIRMED', 'Chicago', 'Detroit', NOW(), NOW());

-- Car ID: 9
INSERT INTO orders (customer_id, car_id, rental_start_date, rental_end_date, total_price, status,
                    pickup_location, drop_location, created_at, updated_at)
VALUES (3, 9, '2025-06-08', '2025-06-10', 100.00, 'CANCELLED', 'San Francisco', 'Los Angeles', NOW(), NOW());
