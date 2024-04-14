<h1>This is a backend project which simulate a platform to manage reservations and everything what need to do it like create rooms, reservations, register guests, add, search, edit and remove these things.
In the project I used Java, Spring Boot, Maven, Postgresql.<br><br></h1>

<h2>Basic operations in this app:</h2><br>

<h3>1.If you want to log in, you need to type correct credentials(login and password) and then a token is generated. The token allows you to enter secured endpoints.<br></h3>
<a href="https://ibb.co/Z12c0SM"><img src="https://i.ibb.co/CsPVf6H/roomreservation.png" alt="roomreservation" border="0"></a><br><br>

<h3>2.To register a guest you need to type required info about the guest. As a response a new object of Guest will be created and saved to database.<br></h3>
<a href="https://ibb.co/47hR74x"><img src="https://i.ibb.co/6mj0mD5/roomreservation.png" alt="roomreservation" border="0"></a><br><br>

<h3>3.If you want to create a new reservation for a guest is necessary to type id of guest, id of room, paymentMethod, start and end of the reservation. A total price is included in resposne and will be calculated automatically. In the case when you give not existing guest or room then a special exception will be thrown. The same will happen when room will be not available or you give wrong dates.<br> </h3>
<a href="https://ibb.co/synwcHh"><img src="https://i.ibb.co/t8gsnXS/roomreservation.png" alt="roomreservation" border="0"></a><br>
<a href="https://ibb.co/n8CFxgH"><img src="https://i.ibb.co/Z1m0qLn/roomreservation.png" alt="roomreservation" border="0"></a> <br><br>

<h3>4.To find available room in date you prefer you must type dates you want and you will see a list of available rooms.A response also contains info about every room like price per night,capacity and facilities etc...</h3><br>
<a href="https://ibb.co/85hdF79"><img src="https://i.ibb.co/C7rMxHQ/roomreservation.png" alt="roomreservation" border="0"></a>


