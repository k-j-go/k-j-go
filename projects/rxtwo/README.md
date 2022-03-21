##### There are two RPC Services
- Web -> rpc folder
- Using rpc-client to retrieve data


- [RPC vs REST API](https://cloud.google.com/blog/products/application-development/rest-vs-rpc-what-problems-are-you-trying-to-solve-with-your-apis)


```shell script
curl --location --request POST 'localhost:8080/rpc/product' \
--header 'Content-Type: application/json' \
--data-raw '{
        "jsonrpc":"2.0",
        "method":"createProduct",
        "params":["dd"],
        "id":1
}'
```

```shell script
curl --location --request POST 'localhost:8080/rpc/product' \
--header 'Content-Type: application/json' \
--data-raw '{
        "jsonrpc":"2.0",
        "method":"findAll",
        "params":[],
        "id":1
}'
```

##### in post body
```shell script
{
    "jsonrpc": "2.0",
    "method": "createProduct",
    "params": [
        "dd"
    ],
    "id": 1
}
```

##### Pull 
```shell script
{
    "jsonrpc": "2.0",
    "method": "findProducts",
    "params": [
        "dd"
    ],
    "id": 1
}
```