# OLB-ODM-Test


## mock-services

In the `mock-services` directory.

To run:

```
python mock-ODM.py
```


## Running tests

```
mvn test

To use `OLBProperties.properties`:

mvn -Dolb.properties=src\main\resources\olb.properties test

mvn -DODM.endpoint=http://localhost:9081 test
```


# Troubleshooting

## Troubleshooting - mock-services

Verify running:

```
curl -s http://localhost:9081/hello
```

Output:

```
Welcome
```

----

Example making ODM call:

```
curl -s http://localhost:9081/DecisionService/rest/v1/validate/validate \
  --header "Content-Type: application/json" \
  --header "Basic: YWRtaW46YWRtaW4=" \
  -d '{"inAndOut": {"emailAddress":"test@test.com", "zipCode":"90210"}}'
```

Output:

```
{"inAndOut": {"emailAddress": "test@test.com", "zipCode": "90210", "response": "hello"}}
```

{
  "inAndOut": {
    "emailAddress": "test@test.com",
    "zipCode": "90210"
  }
}
