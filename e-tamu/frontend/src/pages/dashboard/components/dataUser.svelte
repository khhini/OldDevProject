<script>
  import Modal from "./Modal.svelte";
  import {user} from "../_store";
  import { createEventDispatcher } from "svelte";
  import { goto } from "@sveltech/routify";
  const dispatch = createEventDispatcher();
  export let data;
  export let i;
  let ModalDelete = false;
  let ModalEdit = false;
  let disabled = data._id.$oid == $user._id.$oid;
  function showModalEdit() {
    ModalEdit = !ModalEdit;
  }
  function showModalDelete() {
    ModalDelete = !ModalDelete;
  }
  function deleteApi() {
    dispatch("deleteApi");
  }
  function putApi() {
    fetch("http://localhost:5000/user/" + data._id.$oid, {
      method: "PUT",
      body: JSON.stringify({
        _id: data._id.$oid,
        email: data.email,
        level: data.level,
        nama: data.nama,
        no_hp: data.no_hp,
        password: ""
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8"
      }
    })
      .then(x => x.json())
      .then(json => {
        if (json == "OK") {
          // alert("User Level Updated");
          $goto("/", {}, false);
        }
      });
    // console.log(data.level);
  }
</script>

<Modal showModal={ModalDelete} {data} title={'Delete Data'}>
  Hapus User dengan email {data.email} ?
  <div class="form-inline my-3 float-right">
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
</Modal>
<Modal showModal={ModalEdit} title={'Edit Level'}>
  <form class="user" on:submit|preventDefault={putApi}>
    <div class="form-group">
      <label for="Nama">Level User {data.email}</label>
      <select
        bind:value={data.level}
        class="custom-select my-1 mr-sm-2"
        id="inlineFormCustomSelectPref">
        <option value="user">User</option>
        <option value="admin">Admin</option>
      </select>
    </div>
    <div class="form-inline my-3 float-right">
      <button
        type="button"
        class="btn btn-secondary mx-2"
        data-dismiss="modal"
        on:click={showModalEdit}>
        Batal
      </button>
      <button type="submit" class="btn btn-success btn-user">Simpan</button>
    </div>
  </form>
</Modal>
<tr>
  <td>{i + 1}</td>
  <td>{data.email}</td>
  <td>{data.nama}</td>
  <td>{data.no_hp}</td>
  <td>{data.level}</td>
  <td>
    <button class="btn btn-warning" title="Edit" on:click={showModalEdit} disabled={disabled}>
      <i class="fas fa-pen" />
    </button>
    <button class="btn btn-danger" title="Hapus" on:click={showModalDelete}  disabled={disabled}>
      <i class="fas fa-trash" />
    </button>
  </td>
</tr>
