# Selenium 3

Dit project bevat de basis van een Selenium 3 project.
Volgende features zijn geïmplementeerd:

- Edgedriver
- Chromedriver
- Geckodriver
- Slf4j-over-log4j support
- Browsermob proxy
- Screen recording
- Automatisch updaten van chromedriver

Alle drivers maken gebruik van de gerelateerde services om het spawnen van nieuwe browserinstanties te versnellen.
Er wordt zoveel mogelijk gebruik gemaakt van composition over inheritance om de onderhoudbaarheid te verhogen.
Volgende features moeten nog geïmplementeerd worden:

## Browsermob

De browsermob proxy wordt voor elke test gestart op localhost:8100. Deze onderschept alle calls die van de client naar de server worden gestuurd. Requests die images (.png, .jpg, .jpeg) gaan opvragen worden onderschept en geven standaard een 200 terug. De request zal dan niet naar de server gestuurd worden wat kostbare tijd zal sparen.
Deze functionaliteit staat standaard uit maar kan aangezet worden met de command-line switch *browsermob*:

```sh
mvn clean test -Dbrowsermob
```

## Corporate proxy

Wanneer browsermob gebruikt wordt achter een corporate proxy moet er aan proxy chaining gedaan worden. Dit is mogelijk gemaakt door een boolean mee te geven aan de constructor van de Proxymanager.
De corporate proxy zelf kan geconfigureerd worden in application.properties.

## Screen recording

Elke testrun kan opgenomen worden door gebruik te maken van screen capturing. De functionaliteit hiervan zit in de klasse SeleniumScreenRecorder. Standaard wordt de output gesaved naar de user.home folder van de gebruiker, bijvoorbeeld C:\Users\philippe.

## Automatisch updaten van ChromeDriver

Door de systeemvariabele *useLatestChromeDriver* mee te geven zal de laatste versie van ChromeDriver gedownload worden naar de resources-folder. Deze kan dan gebruikt worden voor de testen.


### Todo
- Screen recording parameterizeren
- Command-line switch inbouwen om browser te kiezen
- Headers toevoegen om authenticatie te bypassen (via proxy)