### Для запуска необходимо заранее установить:
* Docker Desktop
* Node.js
* IntelliJ IDEA
* Git
* Google Chrome (или другой браузер)

### Процедура запуска авто-тестов:
1. Склонируйте текущий репозиторий к себе на компьютер
2. Запустите проект в IntelliJ IDEA
3. В папке gate-simulator выполните команду
> npm I

затем
>npm start

Дождаться появления сообщения:

![npm start](https://github.com/KseniaShepherd/Thesis-PastukhovaK/blob/master/screenshots/npm%20start.png?raw=true)

4. Запустите контейнеры из файла docker-compose.yml командой в терминале:
>docker-compose up

В Docker Desktop должен появится статус RUNNING для обеих баз данных:

![docker](https://github.com/KseniaShepherd/Thesis-PastukhovaK/blob/master/screenshots/docker.png?raw=true)

5.  Запустите приложение расположеное в файле aqa-shop.jar командой в терминале:

для MySQL:

>java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar aqa-shop.jar

для PostgreSQL:

>java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar aqa-shop.jar

Дождаться сообщения в терминале, которое будет означать, что приложение успешно запущено:

![started application](https://github.com/KseniaShepherd/Thesis-PastukhovaK/blob/master/screenshots/started%20application.png?raw=true)

6. Запустите автотесты командой в терминале:

для MySQL:

>./gradlew clean test "-Ddatasource.url=jdbc:mysql://localhost:3306/app"

для PostgreSQL:

>./gradlew clean test "-Ddatasource.url=jdbc:postgresql://localhost:5432/app"



7. По итогам тестирования сгенерируйте отчет с помощью Allure. Отчёт автоматически откроется в браузере с помощью команды в терминале:

>./gradlew allureServe