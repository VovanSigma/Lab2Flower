# Lab 2  XML та парсинг (Java)

## Тема
Обробка XML-документа з описом квітів (теплиця).  
Використання DOM, SAX, StAX парсерів, сортування та валідація за XSD.

## Реалізація

У проєкті є модель предметної області:

`Flower` – основний клас квітки  
`VisualParameters` – колір стебла, листків, середній розмір  
`GrowingTips` – температура, освітлення, полив  
`Soil` – тип ґрунту (enum)  
`Multiplying` – спосіб розмноження (enum)  
`FlowerSorter` – сортування списку квітів (за назвою та середнім розміром)

Парсери XML:

`FlowerDomParser` – парсинг XML за допомогою DOM  
`FlowerSaxParser` – парсинг XML за допомогою SAX  
`FlowerStaxParser` – парсинг XML за допомогою StAX  
`XmlValidator` – перевірка `greenhouse.xml` по схемі `greenhouse.xsd`

Звіт:

`HtmlReportGenerator`  створює простий HTML-файл `flowers.html` зі списком квітів
  (на основі результатів DOM-парсера).

Консольний клас `Main`:

1. Валідуює XML по XSD  
2. Якщо XML валідний запускає всі три парсери  
3. Для DOM-результату додатково:
   виводить початковий список, сортує за назвою, сортує за середнім розміром, генерує HTML-звіт

## Структура проєкту

text
Основні файли:

src/ua/lab2/flower/app/Main.java

src/ua/lab2/flower/model/Flower.java

src/ua/lab2/flower/model/FlowerSorter.java

src/ua/lab2/flower/model/GrowingTips.java

src/ua/lab2/flower/model/Multiplying.java

src/ua/lab2/flower/model/Soil.java

src/ua/lab2/flower/model/VisualParameters.java

src/ua/lab2/flower/parser/FlowerDomParser.java

src/ua/lab2/flower/parser/FlowerSaxParser.java

src/ua/lab2/flower/parser/FlowerStaxParser.java

src/ua/lab2/flower/parser/XmlValidator.java

src/ua/lab2/flower/report/HtmlReportGenerator.java

greenhouse.xml  вихідні дані (квіти в теплиці)
greenhouse.xsd   XSD-схема для валідації

Тести:

test/ua/lab2/flower/parser/FlowerParsersTest.java

Юніт-тести

Використано JUnit 5.

Клас FlowerParsersTest перевіряє:

що XML валідний відносно greenhouse.xsd;

що всі три парсери (DOM, SAX, StAX) коректно читають XML
і повертають список з трьох квітів;

що сортування за назвою та середнім розміром працює очікувано.

