<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Function Page</title>
    <link rel="stylesheet" href="https://unpkg.com/bulma@0.9.0/css/bulma.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/WEB-INF/css/cards.css">

    <style>

        .Header {
            position: fixed;
            width: 100%;
            top: 0px;
            left: 0px;
            right: 0px;
            z-index: 11;
            background: #00aeef;
        }

        .Header .HeaderContainer {
            max-width: 480px;
            width: 100%;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            height: 60px;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            -webkit-box-pack: justify;
            -ms-flex-pack: justify;
            justify-content: space-between;
            margin: 0px auto;
            padding: 0px 1em;
        }

        .Header .ArrowBack {
            cursor: pointer;
            color: #fff;
            margin-right: 20px;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
        }

        .Header .HeaderTitle {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            font-size: 20px;
            line-height: 22px;
            font-weight: 600;
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
            -webkit-box-flex: 1;
            -ms-flex: 1 1 0px;
            flex: 1 1 0;
            color: #fff;
        }

        input::placeholder {
            color: antiquewhite;
        }

        .navbar {
            background: #00aeef;
        }

        .navbar .HeaderTitle {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            font-size: 23px;
            line-height: 22px;
            font-weight: 600;
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
            -webkit-box-flex: 1;
            -ms-flex: 1 1 0px;
            flex: 1 1 0;
            color: #fff;
        }

        .navbar-item {
            color: ghostwhite;
        }

        /* Card start*/
        .card {
            overflow: hidden;
            background: ghostwhite;
            color: var(--bg);
        }

        .card.large {
            border-radius: 5px;
        }

        .title {
            color: #4CE1AF;
            font-size:55px
        }

        .title.no-padding {
            margin-bottom: 0 !important;
        }

        .kt-button {
            display: inline-block;
            font-weight: 500;
            line-height: normal;
            text-align: center;
            vertical-align: middle;
            cursor: pointer;
            width: 100%;
            font-size: 10px;
            background-color: #00aeef;
            color: white;
            border: 0;
            outline: 0;
            -webkit-transition: all 0.35s ease 0s;
            transition: all 0.35s ease 0s;
            text-decoration: none;
            border-radius: 4px;
            padding: 12px 25px;
            text-transform: uppercase;
        }
        .butt {
            display: inline-block;
            font-weight: 500;
            line-height: normal;
            text-align: center;
            vertical-align: middle;
            cursor: pointer;
            background-color: ghostwhite;
            width: 100%;
            font-size: 20px;
            color: #00aeef;
            border: 0;
            outline: 0;
            -webkit-transition: all 0.35s ease 0s;
            transition: all 0.35s ease 0s;
            text-decoration: none;
            border-radius: 4px;
            padding: 12px 25px;
            text-transform: uppercase;
        }

        .kt-button:hover {
            background-color: #00a0da;
        }
    </style>

</head>
<body>

<nav class="navbar">
    <div class="container">
        <div class="navbar-brand">
            <span class="HeaderTitle">GroupHub</span>
            <span class="navbar-burger burger" data-target="navbarMenu">
            <span></span>
            <span></span>
            <span></span>
          </span>
        </div>
        <div id="navbarMenu" class="navbar-menu">
            <div class="navbar-end">
                <form class="navbar-item"  action="/user/toUserModifyPassword" method="post">
                    <button class="kt-button" type="submit">Modify Password</button>
                </form>
                <form class="navbar-item" action="/user/logOff" method="post">
                    <button class="kt-button" type="submit">Log off</button>
                </form>
            </div>
        </div>
    </div>
</nav>
<div class="container">
    <div class="section">
        <div class="columns">
            <div class="column has-text-centered">
                <h1 class="title">Hi ${userName}!</h1><br>
            </div>
        </div>
        <div id="app" class="row columns is-multiline">
            <div v-for="card in cardData" key="card.id" class="column is-4">
                <div class="card large">
                    <div class="card-image">
                        <figure class="image is-16by9">
                            <img src="https://cdn.vox-cdn.com/thumbor/WhBtiSXd3B_c9zlrjW08KU-7_OU=/0x0:2300x1499/1200x800/filters:focal(966x566:1334x934)/cdn.vox-cdn.com/uploads/chorus_image/image/57988089/Movies_end_of_year_2017.0.jpg" alt="Image">
                        </figure>
                    </div>
                    <div class="card-content">
                        <div class="media">
                            <div class="media-content">
                                <p>
                      <span class="title is-6">
                        <form action="/movie/toUserMovie" method="post">
                            <button class="butt" type="submit">Movie</button>
                            </form> </span> </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div v-for="card in cardData" key="card.id" class="column is-4">
                <div class="card large">
                    <div class="card-image">
                        <figure class="image is-16by9">
                            <img src="https://gumlet.assettype.com/freepressjournal%2F2020-02%2Fc5b5280b-4bba-4d22-8e8d-e133c528c7f0%2Fcats.jpg?w=1200&auto=format%2Ccompress&ogImage=true" alt="Image">
                        </figure>
                    </div>
                    <div class="card-content">
                        <div class="media">
                            <div class="media-content">
                                <p>
                      <span class="title is-6">
                        <form action="/book/toUserBook" method="post">
                            <button class="butt" type="submit">Book</button>
                        </form> </span> </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div v-for="card in cardData" key="card.id" class="column is-4">
                <div class="card large">
                    <div class="card-image">
                        <figure class="image is-16by9">
                            <img src="https://goborobudur.com/wp-content/uploads/2016/05/travel.jpg" alt="Image">
                        </figure>
                    </div>
                    <div class="card-content">
                        <div class="media">
                            <div class="media-content">
                                <p>
                      <span class="title is-6">
                        <form action="/travel/toUserTravel" method="post">
                            <button class="butt" type="submit">Travel</button>
                        </form> </span> </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
