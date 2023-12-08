#!/bin/bash

kubectl get namespaces > kubectlNamespacesOutput.txt

{
read
while read -r $namespaceName status age
do
if [ $status = "Terminating" ] ; then
  echo removing namespace $namespaceName
  (
  kubectl proxy &
  kubectl get namespace $namespaceName -o json |jq '.spec = {"finalizers":[]}' >temp.json
  curl -k -H "Content-Type: application/json" -X PUT --data-binary @temp.json 127.0.0.1:8001/api/v1/namespaces/$namespaceName/finalize
  )
fi
done
} < "kubectlNamespacesOutput.txt"

rm temp.json
rm kubectlNamespacesOutput.txt

exit 0