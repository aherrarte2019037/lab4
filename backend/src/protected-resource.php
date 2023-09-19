<?php 
include_once 'config/dbh.php';
include_once '../vendor/autoload.php';

use \Firebase\JWT\JWT;

include_once 'config/cors.php';

$authHeader = getallheaders();
if (isset($authHeader['Authorization']) && $_SERVER['REQUEST_METHOD'] == 'POST') {
    $token = $authHeader['Authorization'];
    $token = explode(" ", $token)[1];

    try {
        $key = "Lab-4-Secret-Key";
        $decoded = JWT::decode($token, $key, array('HS256'));

        http_response_code(200);
        echo json_encode($decoded);
    } catch (Exception $e) {
        http_response_code(401);
        echo json_encode(array('message' => 'Please authenticate'));
    }
} else {
    http_response_code(401);
    echo json_encode(array('message' => 'Please authenticate'));
}