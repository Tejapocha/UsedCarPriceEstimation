# Used Car Price Estimation Project

## Project Overview

This project is a **Spring Boot application** that estimates the resale price of used cars using **Machine Learning (Linear Regression)**.  
It uses car features like **mileage, year, and engine size** to predict the resale price.

The project also provides APIs to:

- View all cars from a CSV dataset
- Estimate the price of a car

---

## Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Maven** for dependency management
- **Smile 3.0.1** (or 2.6.0) for Machine Learning
- **CSV** file for dataset
- **PostgreSQL** (optional if database integration is needed)
- **Lombok** (optional)

---

## Project Structure

UsedCarPriceEstimation/
├─ src/
│ ├─ main/
│ │ ├─ java/com/example/usedcar/
│ │ │ ├─ controller/
│ │ │ ├─ model/
│ │ │ └─ service/
│ │ └─ resources/
│ │ └─ cars.csv
├─ pom.xml
├─ .gitignore
└─ README.md


- `cars.csv`: Dataset containing car details (mileage, year, engineSize, price)
- `PriceEstimationService`: Trains ML model and predicts price
- `CarController`: API endpoints for testing

---

## How to Run

1. **Clone the repository**
```bash
git clone https://github.com/Tejapocha/UsedCarPriceEstimation.git

Open in Eclipse or your preferred IDE

Run the Spring Boot application

Run UsedCarPriceEstimationApplication.java as a Java Application

Test APIs

Using browser, Postman, or cURL

API Endpoints
1. Get all cars
GET /cars


Returns all cars from cars.csv in JSON format

Example response:

[
  {"mileage":50000,"year":2018,"engineSize":1.6,"price":800000},
  {"mileage":30000,"year":2020,"engineSize":2.0,"price":1200000}
]

2. Estimate price for a car
POST /cars/estimate


Request Body (JSON):

{
  "mileage": 50000,
  "year": 2018,
  "engineSize": 1.6
}


Response: Predicted price (number)

Example:

805000.0


cURL Example:

curl -X POST http://localhost:8080/cars/estimate \
-H "Content-Type: application/json" \
-d "{\"mileage\":50000,\"year\":2018,\"engineSize\":1.6}"

Notes

Ensure cars.csv is in src/main/resources/

If you make changes to the dataset or ML features, restart the Spring Boot app

Optional: Add descriptive analytics, trade-in suggestions, or advanced ML models

