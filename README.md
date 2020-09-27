# WeatherService
WeatherService - REST API проект, который занимающийся агрегацией данных о погоде из нескольких источников. 

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
        "temp": "12.36",
        "desc": "Clouds"
    },
    {
        "service": "weatherapi",
        "temp": "13.0",
        "desc": "Overcast"
    },
    {
        "service": "weatherbit",
        "temp": "28.9",
        "desc": "Clear sky"
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
        "temp": "12.36",
        "desc": "Clouds"
    },
    {
        "service": "weatherapi",
        "temp": "13.0",
        "desc": "Overcast"
    },
    {
        "service": "weatherbit",
        "temp": "28.9",
        "desc": "Clear sky"
    }
]
```
