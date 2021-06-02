<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $var1 = $_POST['value'];
    $var2 = $_POST['code'];
    $var3 = $_POST['payment'];

    if (!isset($var2)) {
        return  header("HTTP/1.0 400 Bad Request");
    } else {
        


        $url = 'sandbox/sandbox.php';

        $myvars = 'value=' . $var1 . '&url=' . $_SERVER['HTTP_HOST'] . $_SERVER['PHP_SELF'] . '&payment=' . $var3;

        $ch = curl_init($url);
        curl_setopt($ch, CURLOPT_POST, 1);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $myvars);
        curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
        curl_setopt($ch, CURLOPT_HEADER, 0);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);

        $response = curl_exec($ch);
        $response = json_decode($response, true);
        if ($response['message'] == 'OK') {
            echo $response['payment'].'<br/>'.PHP_EOL;
            echo 'Payment succesfull';
            
        } else {
            echo 'Error payment';
            echo '<p>'.$response['payment'].':  '. $response['message'].'</p>';
        }
        curl_close($ch);
    }
}else{
    http_response_code(401);
}
