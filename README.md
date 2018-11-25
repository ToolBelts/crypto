## Crypto Manager

##### What is crypto manager

Basic encryption to spring boot projects.

##### How do you build project

```bash
$ ./gradlew build
```

##### Configure basic properties in spring boot project

```yaml
security-crypto:
  secret-key: "MY-S3cR3T"
  charset: "UTF-8"
  digest: "SHA-1"
  basic-algorithm: "AES"
  complex-algorithm: "AES/ECB/PKCS5PADDING"
```


