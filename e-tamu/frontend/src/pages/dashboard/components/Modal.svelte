<script>
  import { user } from "../_store";
  export let showModal = false;
  export let modalsize = "";
  export let title = "Modal";
  
  function setShowModal() {
    showModal = !showModal;
  }

  
</script>

{#if showModal}
  <div class="fixed-top pt-5 bg-dark h-100">
    <div class="modal-dialog m-auto {modalsize}">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">{title}</h5>
          <button
            type="button"
            class="close"
            data-dismiss="modal"
            aria-label="Close" />
        </div>
        <div class="modal-body">
          <slot></slot>
        </div>
      </div>
    </div>
  </div>
{/if}
