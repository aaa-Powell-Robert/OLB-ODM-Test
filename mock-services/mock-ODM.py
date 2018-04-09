import json
from flask import Flask, Response, request
app = Flask(__name__)

"""
Mock ODM service

----

curl -s http://localhost:9081/hello

Welcome

----

curl -s http://localhost:9081/DecisionService/rest/v1/validate/validate \
  --header "Content-Type: application/json" \
  --header "Basic: YWRtaW46YWRtaW4=" \
  -d '{"inAndOut": {"emailAddress":"test@test.com", "zipCode":"90210"}}'

{"inAndOut": {"emailAddress": "test@test.com", "zipCode": "90210", "response": "hello"}}

"""

@app.route('/hello')
def api_root():
    return 'Welcome'

@app.route('/DecisionService/rest/v1/validate/validate', methods=['POST'])
def api_articles():
    body = request.get_json()
    emailaddress = body['inAndOut']['emailAddress']
    zipcode = body['inAndOut']['zipCode']
    data = {
        'inAndOut': {
            'emailAddress': emailaddress,
            'zipCode': zipcode,
            'response': 'hello'
        }
    }
    js = json.dumps(data)
    r = Response(js, status=200, mimetype='application/json')
    return(r)

if __name__ == '__main__':
    app.run(port=9081)
