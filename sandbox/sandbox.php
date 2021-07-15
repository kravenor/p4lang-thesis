<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $var1 = $_POST['value'];
    $var2 = $_POST['url'];
    $var3 = $_POST['payment'];
    $transactionID = $_POST['transactionID'];

    $payArray = ['Credit Card', 'PayPal', 'ApplePay'];
   
    $response = '<?xml version="1.0" encoding="utf-8"?>';
    
    if (!in_array($var3, $payArray)) {
        $response .='<response>
            <value>'.$var1.'</value>
            <payment>'.$var3.'</payment>
            <transactionID>'.$transactionID.'</transactionID>
            <message>"Payment method not accepted"</message>
        </response>';
    } else {
        if ($var1 < 50) {
            $response .='<response>
            <value>'.$var1.'</value>
            <payment>'.$var3.'</payment>
            <transactionID>'.$transactionID.'</transactionID>
            <message>"Too low. You need to spend much more"</message>
        </response>';
        } else {
            $response .='<response>
            <value>'.$var1.'</value>
            <payment>'.$var3.'</payment>
            <transactionID>'.$transactionID.'</transactionID>
            <message>"OK"</message>
        </response>';
        }
    }
    sleep(rand(1, 40));

    header("Content-type: text/xml; charset=utf-8");
    echo $response;
} else {
    http_response_code(401);
}
