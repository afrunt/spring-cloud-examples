#!/usr/bin/env bash
kubectl delete -f fibonacci-simple-rc.yaml
kubectl delete -f fibonacci-simple-lb.yaml
kubectl delete configmap fibonacci-simple-cm