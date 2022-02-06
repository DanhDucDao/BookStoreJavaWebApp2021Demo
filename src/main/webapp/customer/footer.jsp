<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
    	
        .grid-container1 {
        	margin-top: 15px;
            background-color: #333;
            display: grid;
            grid-template-columns: 33.33% 33.33% 33.33%;
        }

        .grid-item1 {
            overflow: hidden;
            padding:15px;
        }

        @media screen and (max-width:718px) {
            .grid-container1 {
                grid-template-columns: 100%;
            }
        }

        .contact-line {
            text-align: center;
        }
        .contact-label {
            text-transform: uppercase;
        }
        .contact-label, .contact-content {
            color: #fff;
            margin: 0px;
            
            margin: 5px auto;
        }
    </style>
</head>
<body>
    <footer>
        <div class="grid-container1">
            <div class="map grid-item1">
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d59593.45805826826!2d105.7491724722615!3d21.00902076819475!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ab306caa83a7%3A0xbfe4b316823e38f7!2zSOG7jWMgdmnhu4duIEPDtG5nIG5naOG7hyBCxrB1IGNow61uaCBWaeG7hW4gdGjDtG5nIChQVElUKQ!5e0!3m2!1svi!2s!4v1639533195880!5m2!1svi!2s" width="100%" height="100%" style="border:0;" allowfullscreen="false" loading="lazy"></iframe>
            </div>

            <div class="contact grid-item1">
                <div class="contact-line">
                    <p class="contact-label">Địa chỉ</p>
                    <p class="contact-content">Km 10, Nguyễn Trãi, Hà Đông Hà Nội</p>
                </div>
                <div class="contact-line">
                    <p class="contact-label">Số điện thoại</p>
                    <p class="contact-content">023456789 - 0233344567888</p>
                </div>
                <div class="contact-line">
                    <p class="contact-label">Email</p>
                    <p class="contact-content">ptit@ptit.edu.vn</p>
                </div>
            </div>

            <div class="web-stat grid-item">
                <div class="stat-container">
                    <p class="name"></p>
                    <p class="stat"></p>
                </div>
            </div>
        </div>
    </footer>
</body>
</html>
