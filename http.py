import http.client

conn = http.client.HTTPSConnection("gateway-staging.ncrcloud.com")

headers = {
    'accept': "application/json",
    'content-type': "application/json",
    'nep-enterprise-unit': "String",
    'nep-snapshot-version': "91",
    'nep-correlation-id': "String",
    'nep-organization': "String"
    }

conn.request("GET", "/catalog/item-prices/snapshot", headers=headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))
