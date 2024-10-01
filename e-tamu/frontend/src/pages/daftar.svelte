<script>
  import { url, goto } from "@sveltech/routify";

  const field = {
    _nama: null,
    _email: null,
    _pass: null,
    _passval: null,
    _no_hp: null
  };

  $: valid = field._pass === field._passval;
  function handleSubmit() {
    if (valid) {
      fetch("http://localhost:5000/user", {
        method: "POST",
        body: JSON.stringify({
          email: field._email,
          level: "user",
          nama: field._nama,
          no_hp: field._no_hp,
          password: field._pass
        }),
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        }
      })
        .then(x => x.json())
        .then(json => {
          if (json === "OK") {
            $goto("/login", {}, false);
          } else {
            alert("Email Sudah digunakan");
          }
        });
    } else {
      alert("Password Tidak Sesuai");
    }
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
  <link href="css/sb-admin-2.min.css" rel="stylesheet" />
</head>
<body class="bg-gradient-warning pt-5">

  <div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
      <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
          <div class="col-lg-5 d-flex flex-wrap align-items-center bg-image">
            <img
              src="img/portfolio-1.jpg"
              alt="test"
              class="img-fluid mx-auto pl-3" />
          </div>
          <div class="col-lg-7">
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4">Form Daftar</h1>
              </div>
              <form class="user" on:submit|preventDefault={handleSubmit}>
                <div class="form-group">
                  <input
                    type="text"
                    class="form-control form-control-user"
                    bind:value={field._nama}
                    placeholder="Nama"
                    required />
                </div>
                <div class="form-group">
                  <input
                    type="email"
                    class="form-control form-control-user"
                    bind:value={field._email}
                    placeholder="Email"
                    required />
                </div>
                <div class="form-group row">
                  <div class="col-sm-6 mb-3 mb-sm-0">
                    <input
                      type="password"
                      class="form-control form-control-user"
                      bind:value={field._pass}
                      placeholder="Password"
                      required />
                  </div>
                  <div class="col-sm-6">
                    <input
                      type="password"
                      class="form-control form-control-user"
                      bind:value={field._passval}
                      placeholder="Repeat Password"
                      required />
                  </div>
                </div>
                {#if !valid}
                  <div class="text-center">
                    <p class=" text-danger small">Password Tidak Sama</p>
                  </div>
                {/if}
                <div class="form-group">
                  <input
                    type="text"
                    class="form-control form-control-user"
                    bind:value={field._no_hp}
                    required
                    placeholder="No Hp" />
                </div>
                <button
                  type="submit"
                  class="btn btn-primary btn-user btn-block">
                  Submit
                </button>
              </form>
              <hr />
              <div class="text-center">
                <a class="small" href={$url('/login')}>
                  Already have an account? Login!
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</body>
