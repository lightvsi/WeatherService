# WeatherService
WeatherService - REST API проект, который занимается агрегацией данных о погоде из нескольких источников. 

## How to build and run
```shell script
./mvnw clean install
docker-compose up —build
```

## Rest Example
Получить список данных о погоде из сервисов, которыми пользуется WeatherService.
```http request
GET http://localhost:8080/weather?city=moscow
Content-Type: application/json
```

```json
[
    {
        "service": "openweather",
        "temp": "12",
        "desc": "Clouds"
    },
    {
        "service": "weatherapi",
        "temp": "11.0",
        "desc": "Clear"
    },
    {
        "service": "weatherbit",
        "temp": "14.4",
        "desc": "Scattered clouds"
    }
]
```
Получить данные о состоянии сервисов.
```http request
GET http://localhost:8080/status
Content-Type: application/json
```

```json
[
    {
        "service": "openweather",
        "online": true
    },
    {
        "service": "weatherapi",
        "online": true
    },
    {
        "service": "weatherbit",
        "online": true
    }
]
```
