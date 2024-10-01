<script>
  import { ready, url, params } from "@sveltech/routify";
  import { onMount } from "svelte";
  let user = JSON.parse(sessionStorage.getItem("user"));
  let tamu = [];
  let popmessage = "";
  let page = {
    event: {},
    getApi: () => {
      fetch(
        "http://localhost:5000/event/" + user._id.$oid + "/" + $params.eventId,
        {
          method: "GET",
          headers: {
            "Content-type": "application/json; charset=UTF-8"
          }
        }
      )
        .then(x => x.json())
        .then(json => {
          page.event = json;
        });
    },
    postApi: () => {
      fetch("http://localhost:5000/tamu", {
      method: "POST",
      body: JSON.stringify({
        id_event: page.event._id.$oid,
        data_tamu: tamu
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8"
      }
    })
      .then(x => x.json())
      .then(json => {
        if(json == "OK"){
            alert("Terimakasih Telah Melakukan Registrasi, Selamat Menikmati Acara Kami");
            page.reset();
        }
      });
    },
    reset: () => {
      tamu = [];
      page.event.format_form.forEach(element => {
        tamu.push("");
      });
    }
  };

  onMount(async () => {
    page.getApi();
  });
  $: if (page.event.format_form) {
    page.reset();
  }
</script>

<head>
  <link href="css/stylish-portfolio.min.css" rel="stylesheet" />
</head>
<header class="masthead d-flex">
  <div class="container">
    <div class="card">
      <div class="card-body">
        <div class="row ">
          <div class="col">
            <h3 class="text-center my-3">{page.event.nama_event}</h3>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <img
              src="../img/undraw_welcome_cats_thqn.svg"
              class="img-fluid my-5"
              alt="" />
          </div>
          <div class="col-4">
            <div class="card border-warning mb-3 font-weight-bold">
              <div class="card-header">Form Registrasi</div>
              <div class="card-body">
                <form on:submit|preventDefault={page.postApi}>
                  {#if page.event.format_form}
                    {#each page.event.format_form as field, i}
                      <div class="form-group">
                        <label for="nama_event">{field}</label>
                        <input
                          type="text"
                          class="form-control"
                          bind:value={tamu[i]} />
                      </div>
                    {/each}
                  {/if}
                  <div class="form-inline float-right">
                    <button
                      type="button"
                      class="btn btn-secondary mx-2"
                      data-dismiss="modal"
                      on:click={page.reset}>
                      Reset
                    </button>
                    <button type="submit" class="btn btn-primary">
                      Registrasi
                    </button>
                  </div>
                </form>
              </div>
            </div>

          </div>
        </div>

      </div>
    </div>
  </div>
  <div class="overlay" />
</header>
