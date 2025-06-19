import { AntiFakeGps } from 'anti-fake-gps';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    AntiFakeGps.echo({ value: inputValue })
}
