```shell script
curl --location --request POST 'localhost:9191/rpc/product' \
--header 'Content-Type: application/json' \
--data-raw '{
        "jsonrpc":"2.0",
        "method":"createProduct",
        "params":["dd"],
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