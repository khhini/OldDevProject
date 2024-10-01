<script>
  import { url, goto } from "@sveltech/routify";

  let field = {
    _email: "",
    _password: ""
  };

  function handleSubmit() {
    fetch("http://localhost:5000/auth", {
      method: "POST",
      body: JSON.stringify({
        email: field._email,
        password: field._password
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8"
      }
    })
      .then(x => x.json())
      .then(json => {
        if (json["_id"]) {
          delete json["password"];
          sessionStorage.setItem("user", JSON.stringify(json));
          $goto("/dashboard", {}, false);
        } else {
          alert(json["message"]);
        }
      });
  }
</script>

<style>
  .bg-image {
    background: url("../img/portfolio-1.jpg");
    background-position: center;
    background-size: cover;
  }
</style>

<head>
  <link href="css/sb-admin-2.css" rel="stylesheet" />
</head>

<body class="bg-gradient-warning pt-5">
  <div class="container pt-5">
    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
              <div
                class="col-lg-6 d-flex flex-wrap align-items-center bg-image">
                <img
                  src="img/portfolio-1.jpg"
                  alt="test"
                  class="img-fluid d-block pl-3" />
              </div>
              <div class="col-lg-6">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                  </div>
                  <form class="user" on:submit|preventDefault={handleSubmit}>
                    <div class="form-group">
                      <input
                        type="email"
                        class="form-control form-control-user"
                        aria-describedby="emailHelp"
                        bind:value={field._email}
                        placeholder="Enter Email Address..." required />
                    </div>
                    <div class="form-group">
                      <input
                        type="password"
                        class="form-control form-control-user"
                        bind:value={field._password}
                        placeholder="Password" required/>
                    </div>
                    <button class="btn btn-primary btn-user btn-block">
                      Login
                    </button>
                  </form>
                  <hr />
                  <div class="text-center">
                    <a class="small" href={$url('/daftar')}>
                      Create an Account!
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
