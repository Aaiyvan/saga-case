#!/bin/sh

echo
echo "Create an order successfully"
curl -i -X POST -H Accept:application/json -H Content-Type:application/json http://localhost:8082/api/v1/create-order -d @order-creation.json

echo
echo "Create an order with invalid payment, order should be back to inventory (compensation)"
curl -i -X POST -H Accept:application/json -H Content-Type:application/json http://localhost:8082/api/v1/create-order -d @invalid-payment.json

echo
echo "Create an order that is not in inventory"
curl -i -X POST -H Accept:application/json -H Content-Type:application/json http://localhost:8082/api/v1/create-order -d @no-order-in-inventory.json
