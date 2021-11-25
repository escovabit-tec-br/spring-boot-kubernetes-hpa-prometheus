# Helm App HPA

Aqui temos os comandos que usamos para publicar nossa aplicação

## Instalando

Instalando a aplicação

```bash
export NAMESPACE=l2-app-exemplos
export CUSTOM_VALUES=./values.yaml
export RELEASE=app-hpa
helm upgrade $RELEASE --values $CUSTOM_VALUES --namespace $NAMESPACE --create-namespace --install .
```

## removendo

Fazendo o uninstall do helm

```bash
helm uninstall $RELEASE --namespace $NAMESPACE
```
