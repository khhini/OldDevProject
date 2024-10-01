<script>
  import { user,data} from "./_store";
  import {goto} from '@sveltech/routify';
  import { onMount } from "svelte";
  import DataUser from "./components/dataUser.svelte";
  import DataEvent from "./components/dataEvent.svelte";
  import Modal from "./components/Modal.svelte";

  let modal = {
    showModal: false,
    setShowModal: () => {
      modal.showModal = !modal.showModal;
      formdata.resetForm();
    }
  };
  let formdata = {
    user_id: $user._id.$oid,
    nama_event: "",
    lokasi_event: "",
    tgl_event: "",
    jam_mulai: "",
    jam_selesai: "",
    format_form: ["Nama", "No HP"],
    delKolom: i => {
      formdata.format_form = formdata.format_form.filter((x,y)=> i != y);
    },
    addKolom: () =>{
      formdata.format_form.push("");
      formdata.format_form = formdata.format_form;
    },
    resetForm: ()=>{
      formdata.nama_event = "";
      formdata.lokasi_event = "";
      formdata.tgl_event = "";
      formdata.jam_mulai = "";
      formdata.jam_selesai = "";
      formdata.format_form = ["Nama", "No HP"];
    }
  };

  let page = {
    data: null,
    apiurl: "",
    title: "",
    tabelhead: "",
    kataKunci: "",
    filterData: () => {
      page.resetData();
      if ($user["level"] == "admin")
        page.data = page.data.filter(
          d =>
            d.nama.toLowerCase().includes(page.kataKunci.toLowerCase()) ||
            d.email.toLowerCase().includes(page.kataKunci.toLocaleLowerCase())
        );
      if ($user["level"] == "user")
        page.data = page.data.filter(d =>
          d.nama_event.toLowerCase().includes(page.kataKunci.toLowerCase())
        );
    },
    resetData: () => {
      page.data = $data;
    },
    getApi: url => {
      if ($user["level"] == "user") {
        url += "/" + $user._id.$oid;
      }
      fetch(url, {
        method: "GET",
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        }
      })
        .then(x => x.json())
        .then(json => {
          page.data = json;
          data.set(json);          
        });
    },
    deleteApi: (url,_id) => {
      fetch(url + "/" + _id, {
        method: "DELETE",
        headers: {
          "Content-type": "application/json; charset=UTF-8",
        }
      })
        .then(x => x.json())
        .then(json => {
          console.log(json);
          if (json == "OK") {
            $goto('/',{},false);
          }
        });
      console.log(_id);
    },
    postApi: () =>{
      fetch("http://localhost:5000/event", {
       method: 'POST',
       body: JSON.stringify({
         id_user: formdata.user_id,
         nama_event: formdata.nama_event,
         lokasi_event: formdata.lokasi_event,
         tgl_event: formdata.tgl_event,
         jam_mulai: formdata.jam_mulai,
         jam_selesai: formdata.jam_selesai,
         format_form: formdata.format_form

       }),
       headers: {
         "Content-type": "application/json; charset=UTF-8"
         }
     }).then(x=>x.json()).then(json => {
       if(json == "OK"){
         $goto('/',{},false);
       }
     })
    }
  };
  function deleteEvent(_id){
    page.deleteApi("http://localhost:5000/tamu",_id);
    page.deleteApi(page.apiurl,_id);
  }
  onMount(async () => {
    if ($user["level"] == "admin") {
      page.apiurl = "http://localhost:5000/user";
      page.title = "List User";
      page.tabelhead = ["No", "E-mail", "Nama", "No Hp", "Level", "Aksi"];
    } else if ($user["level"] == "user") {
      page.apiurl = "http://localhost:5000/event";
      page.title = "List E-Tamu";
      page.tabelhead = [
        "No",
        "Nama Event",
        "Lokasi Event",
        "Tanggal Event",
        "Jam Mulai",
        "Jam Selesai",
        "Format Form",
        "Aksi"
      ];
    }
    page.getApi(page.apiurl);
  });
</script>

<div class="card w-100">
  <div class="card-body mx-3">
    <div class="row">
      <h1>{page.title}</h1>
    </div>
    <div class="row">
      <div class="col">
        {#if $user['level'] == 'user'}
          <button
            class="btn btn-primary text-light m-3"
            on:click={modal.setShowModal}>
            + Buku Tamu
          </button>
        {/if}
      </div>
      <div class="col">
        <div class="form-inline my-3 float-right">
          <input
            class="form-control mr-sm-2"
            type="search"
            bind:value={page.kataKunci}
            placeholder="Cari..."
            aria-label="Search" />
          <button
            class="btn btn-outline-primary my-2 my-sm-0"
            on:click={page.filterData}>
            Cari
          </button>
        </div>
      </div>
    </div>

    <div class="row" />
    <table class="table">
      <thead class="thead-dark">
        <tr>
          {#each page.tabelhead as head}
            <th scope="col">{head}</th>
          {/each}
        </tr>
      </thead>
      <tbody>
        {#if page.data && $user['level'] == 'admin'}
          {#each page.data as d, i}
            <DataUser
              data={d}
              {i}
              on:deleteApi={() => {
                page.deleteApi(page.apiurl,d._id.$oid);
              }} />
          {/each}
        {:else if page.data && $user['level'] == 'user'}
          {#each page.data.reverse() as d, i}
            <DataEvent
              data={d}
              {i}
              on:deleteApi={() => {
                deleteEvent(d._id.$oid);
                
              }} />
          {/each}
        {/if}
      </tbody>
    </table>

  </div>
</div>

<Modal showModal={modal.showModal} title={'Buat Buku Tamu'} modalsize={"modal-lg"}>
  <form on:submit|preventDefault={page.postApi}>
  <div class="row">
  <div class="col">
  <div class="form-group">
      <label for="nama_event">Nama Event</label>
      <input
        type="text"
        class="form-control"
        bind:value={formdata.nama_event} />
    </div>
    <div class="form-group">
      <label for="lokasi_event">Lokasi Event</label>
      <textarea class="form-control" bind:value={formdata.lokasi_event} rows="3" />
    </div>
    <div class="form-group row">
      <div class="col">
        <label for="tgl_event">Tanggal Event</label>
        <input
          type="date"
          class="form-control"
          bind:value={formdata.tgl_event} />
      </div>
      <div class="col">
        <label for="Jam Mulai">Jam Mulai</label>
        <input
          type="time"
          class="form-control"
          bind:value={formdata.jam_mulai} />
      </div>
      <div class="col">
        <label for="Jam Selesai">Jam Selesai</label>
        <input
          type="time"
          class="form-control"
          bind:value={formdata.jam_selesai} />
      </div>

    </div>
  </div>
    <div class="col-4">
    <div class="form-inline">
      <label for="lokasi_event">Format Form</label>
      <button type="button" class="btn btn-success btn-sm m-2 float-right" on:click={formdata.addKolom}>
        <i class="fa fa-plus"/>
      </button>
    </div>
    {#each formdata.format_form as d,i}
      <div class="form-group">
        <div class="input-group">
        <input type="text" class="form-control" placeholder="Kolom Baru" bind:value={d}>
        <div class="input-group-prepend">
          <button type="button" class="input-group-text btn btn-danger" on:click={()=>{formdata.delKolom(i)}}>
          <i class="fa fa-times"/>
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
        on:click={modal.setShowModal}>
        Batal
      </button>
      <button type="submit" class="btn btn-primary">Tambah</button>
    </div>
    </div>
    </div>
  </form>
</Modal>
