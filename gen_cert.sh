# /C=SG is for Country
# /ST=SG is for State or province
# /L=Telok Blangah is for Locality name or city
# /O=Tech School is for Organisation
# /OU=Education is for Organisation Unit
# /CN=*.techschool.guru is for Common Name or domain name
# /emailAddress=techschool.guru@gmail.com is for email address

# There are several things that we can use as the alternative name, such as email, DNS, or IP. I will create a new file server-ext.cnf with this content:
# subjectAltName=DNS:*.techienotes.com,DNS:*.techienotes.org,IP:0.0.0.0

rm *.pem

# 1. Generate CA's private key and self-signed certificate
openssl req -x509 -newkey rsa:4096 -days 365 -nodes -keyout ca-key.pem -out ca-cert.pem -subj "/C=SG/ST=SG/L=Locality/O=Techie Notes/OU=Education/CN=*.techienotes-cacert.com/emailAddress=any@gmail.com"

echo "CA's self-signed certificate"
openssl x509 -in ca-cert.pem -noout -text

# 2. Generate web server's private key and certificate signing request (CSR)
openssl req -newkey rsa:4096 -nodes -keyout server-key.pem -out server-req.pem -subj "/C=SG/ST=SG/L=Locality/O=SpringBoot/OU=Server/CN=localhost/emailAddress=any@mail.com"

# 3. Use CA's private key to sign web server's CSR and get back the signed certificate
openssl x509 -req -in server-req.pem -days 60 -CA ca-cert.pem -CAkey ca-key.pem -CAcreateserial -out server-cert.pem -extfile server-ext.cnf

echo "Server's signed certificate"
openssl x509 -in server-cert.pem -noout -text

echo "Verify certificate"
openssl verify -CAfile ca-cert.pem server-cert.pem

# This step is only required for Java related application as they don't understand PEM file configuration. For Ngnix and Apache you may use PEM files directly
# For the SSL certificate, Java doesn’t understand PEM format, and it supports JKS or PKCS#12. This article shows you how to use OpenSSL to convert the existing pem file and its private key into a single PKCS#12 or .p12 file.
# openssl pkcs12 -export -out cert.p12 -in server-cert.pem -inkey server-key.pem

# No password for cert.p12
# openssl pkcs12 -export -out cert.p12 -in server-cert.pem -inkey server-key.pem -passout pass: -nokeys

# Password 123 for cert.p12
# openssl pkcs12 -export -out cert.p12 -in server-cert.pem -inkey server-key.pem -passout pass:123
