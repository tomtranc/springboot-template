# springboot-template
Template for springboot web applications

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
GET http://localhost:8080/endpoint1
```

#### SOAP
View WSDL endpoint:
```
GET http://localhost:8080/ws/countries.wsdl
```
Execution request:
```
curl --header "content-type: text/xml" -d @/Users/tomtran/myRepos/springboot-template/src/test/test/resources/soap/request.xml http://localhost:8080/ws | xmllint --format -
```
Execution response:
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