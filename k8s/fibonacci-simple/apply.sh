#!/usr/bin/env bash
kubectl create configmap fibonacci-simple-cm --from-file=config/bootstrap.yml
kubectl apply -f fibonacci-simple-rc.yaml
kubectl apply -f fibonacci-simple-lb.yaml