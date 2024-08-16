#!/bin/sh

curl -X PUT -H "Content-Type: application/json" -d @debezium/connectors/reservation-outbox-connector.json http://localhost:8083/connectors/reservation-outbox-connector/config
curl -X PUT -H "Content-Type: application/json" -d @debezium/connectors/hotel-outbox-connector.json http://localhost:8083/connectors/hotel-outbox-connector/config
curl -X PUT -H "Content-Type: application/json" -d @debezium/connectors/payment-outbox-connector.json http://localhost:8083/connectors/payment-outbox-connector/config

