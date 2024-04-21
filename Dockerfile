# Использование образа Selenium Standalone Chrome
FROM selenium/standalone-chrome:latest

# Переход на пользователя root для установки дополнительных пакетов
USER root

# Установка Java и Maven
RUN apt-get update && \
    apt-get install -y openjdk-11-jdk maven && \
    rm -rf /var/lib/apt/lists/*  # Очистка кеша apt после установки

# Проверка установленной версии Java
RUN java -version && javac -version

# Установка переменных среды для Java и Maven
ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64
ENV PATH $JAVA_HOME/bin:$PATH

# Установка рабочего каталога для приложения
WORKDIR /app

# Копирование исходного кода проекта в контейнер
COPY . /app

# Запуск тестов с использованием Maven
CMD ["mvn", "test"]
#CMD tail -f /dev/null