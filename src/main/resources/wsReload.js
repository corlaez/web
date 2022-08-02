const ws = new WebSocket("ws://localhost:PORT/dev/subscribe")
ws.onopen=()=>console.log("open")
ws.onmessage=()=>console.log("reload")||location.reload()
ws.onerror=(ev)=>console.log(ev)
ws.onclose=()=>{console.log("close")}