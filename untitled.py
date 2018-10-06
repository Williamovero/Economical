import http.client

conn = http.client.HTTPSConnection("gateway-staging.ncrcloud.com")

headers = {
    'accept': "application/json",
    'content-type': "application/json",
    'nep-application-key': "8a00860b6641a0ae016646edbaa3000d",
    'nep-organization': "ncr-market"
    }

conn.request("GET", "/catalog/item-prices/String/String", headers=headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))