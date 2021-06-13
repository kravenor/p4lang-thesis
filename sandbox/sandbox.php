<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $var1 = $_POST['value'];
    $var2 = $_POST['url'];
    $var3 = $_POST['payment'];
    $transactionID = $_POST['transactionID'];

    $payArray = ['Credit Card', 'Paypal', 'ApplePay'];
    $response = array();
    if (!in_array($var3, $payArray)) {
        $response = [
            "value" => $var1,
            "payment" => $var3,
            "transactionID" => $transactionID,
            "message" => "Payment method not accepted"
        ];
    } else {

        if ($var1 < 50) {
            $response = [
                "value" => $var1,
                "payment" => $var3,
                "transactionID" =>$transactionID,
                "message" => "Too low. You need to spend much more"
            ];
        } else {
            $response = [
                "value" => $var1,
                "payment" => $var3,
                "transactionID" =>$transactionID,
                "message" => "OK"
            ];
        }
    }
    sleep(rand(1, 10));

    header("Content-Type: application/json");
    echo json_encode($response);
} else {
    http_response_code(401);
}
