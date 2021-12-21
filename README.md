## simple-springbootcache-demo

### This application explains the basic usage of Caching and SSL security in Spring Boot Application

#### Steps to generate self-signed CA Root and Server Certificates

* Execute shell to generate both CA Root and Server Certificate (In PEM format)

```shell
sh gen_cert.sh
```

* Execute the command below to generate p12 Certificate for SpringBoot SSL configuration

```shell
openssl pkcs12 -export -out cert.p12 -in server-cert.pem -inkey server-key.pem -passout pass:123
```

* Add SSL configuration to application configuration

```yaml
server:
  ssl:
    enabled: true
    key-store: classpath:cert.p12
    key-store-password: 123
    key-store-type: pkcs12
  port: 8443
```

* Postman Configuration:
    * Add the PEM certificate of CA-Root (In our case it's ca-cert.pem) by switch
      on `Preferences->Certificates->CA Certificates->ON`
    * Upload ca-cert.pem

#### URLs to access rest endpoints

* addBook: https://localhost:8443/v1/book
* getBook: https://localhost:8443/v1/book/1
* updateBook: https://localhost:8443/v1/book
* deleteBook: https://localhost:8443/v1/book/1

### Spring Doc (Swagger)

* https://localhost:8443/swagger-ui.html