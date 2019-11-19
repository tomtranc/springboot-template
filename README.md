# springboot-template
Demo Template for springboot web applications

## How to build and deploy
```
# to build
mvn clean install

# to deploy on tomcat (wrapped inside war)
java -jar target/springboot-example-0.0.1-SNAPSHOT.war
```

#### Recommended Project Structure
```
com
 +- example
     +- app
         +- Application.java
         |
         +- customer
         |   +- Customer.java
         |   +- CustomerController.java
         |   +- CustomerService.java
         |   +- CustomerRepository.java
         |
         +- order
             +- Order.java
             +- OrderController.java
             +- OrderService.java
             +- OrderRepository.java
```
#### REST
Execution:
```
GET     http://localhost:9090/
GET     http://localhost:9090/customers
POST    http://localhost:9090/customers
PUT     http://localhost:9090/customers/{id}
```

#### SOAP
View WSDL endpoint:
```
GET http://localhost:9090/ws/countries.wsdl
GET http://localhost:9090/ws/scorecard.wsdl
```
Execution request:
```
curl -H "content-type: text/xml" -d @/Users/tomtran/myRepos/springboot-template/src/test/test/resources/soap/countriesRequest.xml http://localhost:9090/ws | xmllint --format -
curl -H "content-type: text/xml" -d @/Users/tomtran/myRepos/springboot-template/src/test/test/resources/soap/scorecardRequest.xml http://localhost:9090/ws | xmllint --format -
```
Execution response for **countriesRequest**:
```xml
<?xml version="1.0"?>
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
  <SOAP-ENV:Header/>
  <SOAP-ENV:Body>
    <ns2:getCountryResponse xmlns:ns2="http://namespace.my/jaxb/output/">
      <ns2:country>
        <ns2:name>Spain</ns2:name>
        <ns2:population>46704314</ns2:population>
        <ns2:capital>Madrid</ns2:capital>
        <ns2:currency>EUR</ns2:currency>
      </ns2:country>
    </ns2:getCountryResponse>
  </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```
Execution response for **scorecard**:
```xml
<?xml version="1.0"?>
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
  <SOAP-ENV:Header/>
  <SOAP-ENV:Body>
    <ns2:PMMLOutput xmlns:ns2="http://namespace.my/jaxb/scorecard/">
      <ns2:finalScore>69.0</ns2:finalScore>
      <ns2:reasonCode1>good</ns2:reasonCode1>
      <ns2:reasonCode2>good</ns2:reasonCode2>
      <ns2:reasonCode3>good</ns2:reasonCode3>
      <ns2:reasonCode4>good</ns2:reasonCode4>
    </ns2:PMMLOutput>
  </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```