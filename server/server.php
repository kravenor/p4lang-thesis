<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $var1 = $_POST['value'];
    $var2 = $_POST['code'];
    $var3 = $_POST['payment'];
    $var4 = $_POST['transaction_id'];

    if (!isset($var2)) {
        return  header("HTTP/1.0 400 Bad Request");
    } else {

        $url = 'sandbox/sandbox.php';

        echo $url;
    }
} else {
    http_response_code(401);
}
