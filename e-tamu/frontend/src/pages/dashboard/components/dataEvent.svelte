<script>
  import Modal from "./Modal.svelte";
  import { url, goto } from "@sveltech/routify";
  import { createEventDispatcher } from "svelte";
  const dispatch = createEventDispatcher();
  export let data;
  export let i;

  let ModalDelete = false;
  let ModalEdit = false;
  let ModalTambah = false;

  function showModalEdit() {
    ModalEdit = !ModalEdit;
  }
  function showModalDelete() {
    ModalDelete = !ModalDelete;
  }
  function delKolom(i) {
    data.format_form = data.format_form.filter((x, y) => i != y);
  }
  function addKolom() {
    data.format_form.push("");
    data.format_form = data.format_form;
  }

  function deleteApi() {
    dispatch("deleteApi");
  }
  function putApi() {
    fetch("http://localhost:5000/event/" + data._id.$oid, {
      method: "PUT",
      body: JSON.stringify({
        nama_event: data.nama_event,
        lokasi_event: data.lokasi_event,
        tgl_event: data.tgl_event,
        jam_mulai: data.jam_mulai,
        jam_selesai: data.jam_selesai,
        format_form: data.format_form
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8"
      }
    })
      .then(x => x.json())
      .then(json => {
        if (json == "OK") {
          $goto("/", {}, false);
        }
      });
  }
</script>

<style>
  .action-btn {
    width: 32px;
  }
</style>

<Modal showModal={ModalDelete} {data} title={'Hapus Event'}>
  Hapus Data Event {data.nama_event} ?
  <div class="row">
    <div class="col">
      <div class="form-inline float-right">
        <button
          type="button"
          class="btn btn-secondary mx-2"
          data-dismiss="modal"
          on:click={showModalDelete}>
          Batal
        </button>
        <button type="button" class="btn btn-danger" on:click={deleteApi}>
          Hapus
        </button>
      </div>
    </div>
  </div>
</Modal>

<tr>
  <td>{i + 1}</td>
  <td>{data.nama_event}</td>
  <td>{data.lokasi_event}</td>
  <td>{data.tgl_event}</td>
  <td>{data.jam_mulai}</td>
  <td>{data.jam_selesai}</td>
  <td>{data.format_form}</td>
  <td width="130px">
    <a
      href={$url('../:eventId', { eventId: data._id.$oid })}
      class="btn btn-primary btn-sm action-btn text-white"
      title="Detail">
      <i class="fas fa-info" />
    </a>
    <button
      class="btn btn-warning btn-sm action-btn"
      title="Edit"
      on:click={showModalEdit}>
      <i class="fas fa-pen" />
    </button>
    <button
      class="btn btn-danger btn-sm action-btn"
      title="Hapus"
      on:click={showModalDelete}>
      <i class="fas fa-trash" />
    </button>
  </td>
</tr>

<Modal showModal={ModalEdit} title={'Buat Buku Tamu'} modalsize={'modal-lg'}>
  <form on:submit|preventDefault={putApi}>
    <div class="row">
      <div class="col">
        <div class="form-group">
          <label for="nama_event">Nama Event</label>
          <input
            type="text"
            class="form-control"
            bind:value={data.nama_event} />
        </div>
        <div class="form-group">
          <label for="lokasi_event">Lokasi Event</label>
          <textarea
            class="form-control"
            bind:value={data.lokasi_event}
            rows="3" />
        </div>
        <div class="form-group row">
          <div class="col">
            <label for="tgl_event">Tanggal Event</label>
            <input
              type="date"
              class="form-control"
              bind:value={data.tgl_event} />
          </div>
          <div class="col">
            <label for="Jam Mulai">Jam Mulai</label>
            <input
              type="time"
              class="form-control"
              bind:value={data.jam_mulai} />
          </div>
          <div class="col">
            <label for="Jam Selesai">Jam Selesai</label>
            <input
              type="time"
              class="form-control"
              bind:value={data.jam_selesai} />
          </div>

        </div>
      </div>
      <div class="col-4">
        <div class="form-inline">
          <label for="lokasi_event">Format Form</label>
          <button
            type="button"
            class="btn btn-success btn-sm m-2 float-right"
            on:click={addKolom}>
            <i class="fa fa-plus" />
          </button>
        </div>
        {#each data.format_form as d, i}
          <div class="form-group">
            <div class="input-group">
              <input
                type="text"
                class="form-control"
                placeholder="Kolom Baru"
                bind:value={d} />
              <div class="input-group-prepend">
                <button
                  type="button"
                  class="input-group-text btn btn-danger"
                  on:click={() => {
                    delKolom(i);
                  }}>
                  <i class="fa fa-times" />
                </button>
              </div>
            </div>
          </div>
        {/each}
      </div>
    </div>
    <div class="row">
      <div class="col">
        <div class="form-inline float-right">
          <button
            type="button"
            class="btn btn-secondary mx-2"
            data-dismiss="modal"
            on:click={showModalEdit}>
            Batal
          </button>
          <button type="submit" class="btn btn-primary">Update</button>
        </div>
      </div>
    </div>
  </form>
</Modal>
