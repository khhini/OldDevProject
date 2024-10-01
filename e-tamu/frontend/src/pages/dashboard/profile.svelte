<script>
  import { user } from "./_store.js";
  import EditModal from "./components/Modal.svelte";
  export let data = $user;
  let showModal = false;
  let changepass = false;
  data.password = "";
  data.valpass = "";
  $: valid = data.password == data.valpass;

  function setShowModal() {
    showModal = !showModal;
  }
  function putApi() {
    // console.log(data._id.$oid);
    fetch("http://localhost:5000/user/" + data._id.$oid, {
      method: "PUT",
      body: JSON.stringify({
        _id: data._id.$oid,
        email: data.email,
        level: data.level,
        nama: data.nama,
        no_hp: data.no_hp,
        password: data.password
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8"
      }
    })
      .then(x => x.json())
      .then(json => {
        if (json == "OK") {
          delete data["password"];
          delete data["valpass"];
          user.set(data);
          alert("Profile Updated");
          setShowModal();
        }
      });
  }
</script>

<EditModal {showModal}>
  <form class="user" on:submit|preventDefault={putApi}>
    <div class="form-group">
      <label for="Nama">Nama</label>
      <input
        type="text"
        class="form-control form-control-user"
        bind:value={data.nama}
        placeholder="Nama"
        required />
    </div>
    <div class="form-group">
      <label for="Email">E-Mail</label>
      <input
        type="email"
        class="form-control form-control-user"
        bind:value={data.email}
        placeholder="Email"
        required />
    </div>
    <div class="form-group">
      <label for="Password">
        <input
          type="checkbox"
          bind:checked={changepass} />
        Password
      </label>
    </div>
    {#if changepass}
      <div class="form-group row">
        <div class="col-sm-6 mb-3 mb-sm-0">
          <input
            type="password"
            class="form-control form-control-user"
            bind:value={data.password}
            placeholder="Password"
            required />
        </div>
        <div class="col-sm-6">
          <input
            type="password"
            class="form-control form-control-user align-bottom"
            bind:value={data.valpass}
            placeholder="Repeat Password"
            required />
        </div>
      </div>
      {#if !valid}
        <div class="text-center">
          <p class=" text-danger small">Password Tidak Sama</p>
        </div>
      {/if}
    {/if}
    <div class="form-group">
      <label for="No_HP">No Hp</label>
      <input
        type="text"
        class="form-control form-control-user"
        bind:value={data.no_hp}
        required
        placeholder="No Hp" />
    </div>
    <div class="form-group row">
      <div class="col">
        <button
          class="btn btn-secondary btn-user btn-block"
          on:click={setShowModal}>
          Batal
        </button>
      </div>
      <div class="col">

        <button
          type="submit"
          class="btn btn-success btn-user btn-block"
          disabled={!valid}>
          Simpan
        </button>
      </div>
    </div>
  </form>
</EditModal>

<div class="card">
  <div class="card-body">
    <div class="row">
      <div class="col">
        <img src="../img/undraw_profile_6l1l(1).svg" class="img-fluid" alt="" />
      </div>
      <div class="col">
        <div class="row">
          <h1>Profile User</h1>
          <table class="table">
            <tbody>
              <tr>
                <th class="thead-dark">Nama</th>
                <td>{$user.nama}</td>
              </tr>
              <tr>
                <th>E-Mail</th>
                <td>{$user.email}</td>
              </tr>
              <tr>
                <th>No HP</th>
                <td>{$user.no_hp}</td>
              </tr>
              <tr>
                <th>Level</th>
                <td>{$user.level}</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="row">
          <div class="col">
            <button
              class="btn btn-warning float-right "
              title="Edit"
              on:click={setShowModal}>
              <i class="fas fa-pen" />
            </button>
          </div>

        </div>
      </div>
    </div>

  </div>
</div>
