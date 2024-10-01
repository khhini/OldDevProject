<script>
  import { ready, url, params } from "@sveltech/routify";
  import { user } from "./_store";
  import { onMount } from "svelte";
  var csv = "";
  let page = {
    data_event: false,
    data_tamu: false,
    apiurl_tamu: "http://localhost:5000/tamu/" + $params.eventId,
    apiurl_event:
      "http://localhost:5000/event/" + $user._id.$oid + "/" + $params.eventId,
    getApi: url => {
      fetch(url, {
        method: "GET",
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        }
      })
        .then(x => x.json())
        .then(json => {
          if (url == page.apiurl_tamu) {
            page.data_tamu = json;
          } else if (url == page.apiurl_event) {
            page.data_event = json;
          }
        });
    }
  };

  $: if (page.data_tamu && page.data_event) {
    csv = page.data_event.format_form.toString() + "\n";

    page.data_tamu.forEach(element => {
      csv += element.data_tamu.toString();
      csv += "\n";
    });
  }

  onMount(async () => {
    page.getApi(page.apiurl_event);
    page.getApi(page.apiurl_tamu);
  });
</script>

<div class="card w-100">
  <div class="card-body mx-3">
    <div class="row">
      <h1>Detail Event</h1>
    </div>
    <div class="row">
      <a
        href={$url('/dashboard')}
        type="button"
        class="btn btn-primary btn-sm text-white">
        <i class="fa fa-arrow-left" />
      </a>
    </div>
    <div class="row">
      <div class="col">
        <img src="../img/undraw_events_2p66.svg" class="img-fluid" alt="" />
      </div>
      <div class="col">
        <div class="row">
          {#if page.data_event}
            <table class="table">
              <tbody>
                <tr>
                  <th width="200px">Nama Event</th>
                  <td>{page.data_event.nama_event}</td>
                </tr>
                <tr>
                  <th>Lokasi Event</th>
                  <td>{page.data_event.lokasi_event}</td>
                </tr>
                <tr>
                  <th>Tanggal Event</th>
                  <td>{page.data_event.tgl_event}</td>
                </tr>
                <tr>
                  <th>Mulai</th>
                  <td>{page.data_event.jam_mulai}</td>
                </tr>
                <tr>
                  <th>Selesai</th>
                  <td>{page.data_event.jam_selesai}</td>
                </tr>
                <tr>
                  <th>Link Form E-Tamu</th>
                  <td>
                    <a href={$url('/:eventId', { eventId: $params.eventId })}>
                      Link
                    </a>
                  </td>
                </tr>
                <tr>
                  <td colspan="2">
                    <a
                      class="btn btn-primary float-right"
                      href="data:application/octet-stream,{encodeURI(csv)}"
                      download="Data E-Tamu_{page.data_event.nama_event}.csv">
                      Download Data
                    </a>

                  </td>
                </tr>
              </tbody>
            </table>
          {/if}
        </div>
      </div>
    </div>

    <div class="row" />
    <div class="row">
      <h2>Daftar Tamu</h2>
    </div>
    <table class="table">
      <thead class="thead-dark">
        <th>No</th>
        {#if page.data_event.format_form}
          {#each page.data_event.format_form as head}
            <th>{head}</th>
          {/each}
        {/if}
      </thead>
      <tbody>
        {#if page.data_tamu}
          {#each page.data_tamu as tamu, i}
            <tr>
              <td>{i + 1}</td>
              {#each tamu.data_tamu as data}
                <td>{data}</td>
              {/each}
            </tr>
          {/each}
        {/if}
      </tbody>
    </table>

  </div>
</div>
