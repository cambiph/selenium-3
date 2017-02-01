# Selenium 3

Dit project bevat de basis van een Selenium 3 project.
Volgende features zijn geïmplementeerd:

- Edgedriver
- Chromedriver
- Geckodriver
- Slf4j-over-log4j support
- Browsermob proxy

Alle drivers maken gebruik van de gerelateerde services om het spawnen van nieuwe browserinstanties te versnellen.
Er wordt zoveel mogelijk gebruik gemaakt van composition over inheritance om de onderhoudbaarheid te verhogen.
Volgende features moeten nog geïmplementeerd worden:

- Images niet laden om testruns te versnellen (via proxy)
- Headers toevoegen om authenticatie te bypassen (via extensie of proxy)