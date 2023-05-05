#!/bin/sh
# version 1.0
# date 01.05.2023
#=====================================================================================
# This script creates a mapping for elastic index 'content'
#=====================================================================================

curl -XPUT -H 'Content-Type: application/json' -d '{
 "mappings": {
    "dynamic": false,
    "properties": {
          "link": {
            "type": "keyword"
          },
          "content_type": {
            "type": "text",
               "fields": {
                  "keyword": {
                  "type": "keyword"
              }
            }
          },
          "tag": {
            "type": "text",
              "fields": {
                  "keyword": {
                  "type": "keyword"
                }
              }
          },
          "name": {
            "type": "keyword"
          },
          "description": {
            "type": "keyword"
          },
          "priority": {
            "type": "text",
              "fields": {
                 "keyword": {
                 "type": "keyword"
               }
            }
         },
          "status": {
            "type": "keyword"
         },
          "rating": {
            "type": "long"
         },
          "created_date": {
            "type": "date",
            "format": "strict_date_time_no_millis",
            "doc_values": true
          },
          "updated_date": {
            "type": "date",
            "format": "strict_date_time_no_millis",
            "doc_values": true
          }
      }
  }
}' http://localhost:9200/content
