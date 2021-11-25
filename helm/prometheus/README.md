# Stack Prometheus

Aqui temos os comandos que foram usados para fazer a construção da stack do prometheus no K8S utilizados no POST.

GitHub do time do Helm Promethes: <https://github.com/prometheus-community/helm-charts>

Todos os deploys (helm) vai ter um arquivo values com as customizaões que precisamos fazer.

As customizações são para rodar em um minikube.

## Instalando

Adcionando o prometheus-community repositorio.

```bash
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo update
```

### kube-prometheus-stack

Instalando a stack de prometheus

```bash

export NAMESPACE=l4-mntg-prometheus
export CUSTOM_VALUES=./kube-prometheus-stack-values.yml
export RELEASE=prometheus
helm upgrade $RELEASE prometheus-community/kube-prometheus-stack --values $CUSTOM_VALUES  --namespace $NAMESPACE --create-namespace --install
```

Pegando a senha do grafana

```bash
export GRAFANA_ADMIN_PASSWORD=$(kubectl get secret --namespace l1-mntg-grafana $RELEASE-grafana -o jsonpath="{.data.admin-password}" | base64 --decode)
echo $GRAFANA_ADMIN_PASSWORD
```

### prometheus-adapter

Instalando o prometheus-adapter

```bash
export NAMESPACE=l4-mntg-prometheus
export CUSTOM_VALUES=./prometheus-adapter-values.yml
export RELEASE=prometheus-adapter
helm upgrade $RELEASE prometheus-community/prometheus-adapter --values $CUSTOM_VALUES  --namespace $NAMESPACE --create-namespace --install
```
